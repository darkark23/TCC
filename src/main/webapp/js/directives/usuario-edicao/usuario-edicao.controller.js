'use strict'
angular.module('tccApp').controller('UsuarioEdicaoController',
		[ "$scope", '$state', '$q', '$timeout', 'usuarioEdicaoService', 'loginService', '$rootScope', function($scope, $state, $q, $timeout, usuarioEdicaoService, loginService,  $rootScope) {

	$scope.listaCidade = null;
	$scope.listaEstado = null;
	$scope.listaPerfil = null;
	$scope.listaCidadeFiltrada = null;
	$scope.novoUsuario = false;
	$scope.erro =  {
		texto:null,
		show:false
	}
	$scope.senhaConfimacao = null;

	$scope.listaSexo = [
		{descricao : "Feminino", valor : "F"},
		{descricao : "Masculino", valor : "M"}
	];

	$scope.listaTipoTelefone = [
		{descricao : "Residencial", valor : "R"},
		{descricao : "Celular", valor : "C"},
		{descricao : "Trabalho", valor : "T"}
	];

	synth.cancel();
	construir();

	function construir(){
		//configurarMascara();
		recuperarUsuario();
		recuperarListaEdicao();
		if($state.params.id){
			recuperarUsuarioEdicao();
		}else {
			$scope.usuario = {
				pessoa : {
					nome:null,
					cpf:null,
					rg:null,
					sexo:null,
					endereco:{
						cep:null,
						estadoId:null,
						cidadeId:null,
						bairro:null,
						complemento:null
					},
					telefone:{
						tipo:null,
						numero:null
					}
				},
				login:null,
				senha:null,
				email:null,
				perfil:null
			}
		}
	}

	function configurarMascara() {
		$(document).ready(function(){
			//$('#birth-date').mask('00/00/0000');
			$('#telefone').mask('(000)00000-0000');
		});
	}

	function recuperarUsuario() {
		if($scope.dados){
			loginService.confirmarUsuario($scope.dados,
				function (usuarioPerfil) {
					if (usuarioPerfil.existente == true){
						$rootScope.usuarioPerfil = usuarioPerfil;
					}else{

					}
				},function () {
				});
		}else {
			$scope.novoUsuario = true;
		}
	}

	function recuperarListaEdicao() {
		usuarioEdicaoService.getListasCadastro(function (listas) {
			$scope.listaCidade = listas.listaCidade;
			$scope.listaEstado = listas.listaEstado;
			$scope.listaPerfil = listas.listaPerfil;
		},function () {

		});
	}

	function recuperarUsuarioEdicao() {
		usuarioEdicaoService.getCadastroUsuario($state.params.id,
			function (usuarioEdicao) {
				$scope.usuario = usuarioEdicao;
			},function () {
			});

	}

	function limparFormulario() {
		prepararComboBoxs();
	}

	function prepararComboBoxs() {
		prepararComboSexo();
	}

	function prepararComboSexo() {
	}

    $scope.filtrarCidade = function () {
	    if ($scope.usuario.pessoa.endereco.estadoId){
            $scope.listaCidadeFiltrada = $scope.listaCidade.filter(function (cidade) {
                return cidade.estadoId == $scope.usuario.pessoa.endereco.estadoId;
            })
        }else{
            $scope.listaCidadeFiltrada = null;
            $scope.usuario.pessoa.endereco.cidadeId = null;
        }
    }

	$scope.sair = function () {
		$state.go('principal', {}, {reload : true});
	}

	$scope.voltar = function () {
		$state.go('adminListaUsuario', {}, {reload : true});
	}

	$scope.cadastrarNovo = function () {
		if(verificarCampos()){
			if($state.params.indicadorChave == "true"){
				removerFormatacao();
				if($state.params.indicadorChave == "true"){
					$scope.usuario.perfil = 3;
				}
				usuarioEdicaoService.salvarUsuario($scope.usuario,function (retorno) {
					$state.go('principal', {}, {reload : true});
				},function () {
					$state.go('principal', {}, {reload : true});
				});
			}else {
				removerFormatacao();
				usuarioEdicaoService.salvarUsuario($scope.usuario,function (retorno) {
					$state.go('adminListaUsuario', {}, {reload : true});
				},function () {
					$state.go('adminListaUsuario', {}, {reload : true});
				});
			}
		} else {
			$scope.erro.show = true;
			window.scrollTo(500, 0);
		}
	}

	$scope.atualizar = function () {
		if(verificarCampos()){
			removerFormatacao();
			usuarioEdicaoService.salvarUsuario($scope.usuario,function (retorno) {
				$state.go('adminListaUsuario', {}, {reload : true});
			},function () {
				$state.go('adminListaUsuario', {}, {reload : true});
			});
		} else {
			$scope.erro.show = true;
			window.location.hash = '#erro-box';
		}
	}

	$scope.mostrarVoltar  = function () {
		if($state.params.indicadorChave == "false"){
			return true;
		}else {
			return false;
		}
	}

	$scope.mostrarSair  = function () {
		if($state.params.indicadorChave == "true"){
			return true;
		}else {
			return false;
		}
	}

	$scope.mostarAtualizar  = function () {
		if($state.params.indicadorNovo == "false"){
			return true;
		}else {
			return false;
		}
	}

	$scope.mostrarRegistrar  = function () {
		if($state.params.indicadorNovo == "true"){
			return true;
		}else {
			return false;
		}
	}
	
	function removerFormatacao() {
		$scope.usuario.pessoa.cpf = $scope.usuario.pessoa.cpf.replace(/[^\w\s]/gi, "");
		$scope.usuario.pessoa.rg = $scope.usuario.pessoa.rg.replace(/[^\w\s]/gi, "");
		$scope.usuario.pessoa.endereco.cep = $scope.usuario.pessoa.endereco.cep.replace(/[^\w\s]/gi, "");
		$scope.usuario.pessoa.telefone.numero = $scope.usuario.pessoa.telefone.numero.replace(/[^\w\s]/gi, "");
		$scope.usuario.pessoa.telefone.numero = $scope.usuario.pessoa.telefone.numero.replace(/\s+/g, "");
	}

	function verificarCampos() {
		if(!$scope.usuario.pessoa.nome){
			$scope.erro.texto = "Campo nome obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.cpf){
			$scope.erro.texto = "Campo CPF obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.rg){
			$scope.erro.texto = "Campo RG obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.sexo){
			$scope.erro.texto = "Campo sexo obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.dataNascimento){
			$scope.erro.texto = "Campo data nascimento obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.endereco.cep){
			$scope.erro.texto = "Campo CEP obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.endereco.estadoId){
			$scope.erro.texto = "Campo estado obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.endereco.cidadeId){
			$scope.erro.texto = "Campo cidade obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.endereco.bairro){
			$scope.erro.texto = "Campo bairro obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.endereco.complemento){
			$scope.erro.texto = "Campo complemento obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.telefone.tipo){
			$scope.erro.texto = "Campo tipo telefone obrigatório!";
			return false;
		} else if (!$scope.usuario.pessoa.telefone.numero){
			$scope.erro.texto = "Campo número telefone obrigatório!";
			return false;
		} else if (!$scope.usuario.login){
			$scope.erro.texto = "Campo login obrigatório!";
			return false;
		} else if (!$scope.usuario.senha){
			$scope.erro.texto = "Campo senha obrigatório!";
			return false;
		} else if ($scope.usuario.senha != $scope.senhaConfimacao){
			$scope.erro.texto = "Cofinmação de senha incorreta!";
			return false;
		} else if (!$scope.usuario.email){
			$scope.erro.texto = "Campo email obrigatório!";
			return false;
		} else if (!$scope.usuario.perfil){
			$scope.erro.texto = "Campo perfil obrigatório!";
			return false;
		}
		return true;
	}
	
}]);
