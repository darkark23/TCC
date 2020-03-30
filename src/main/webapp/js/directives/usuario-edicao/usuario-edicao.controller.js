'use strict'
angular.module('tccApp').controller('UsuarioEdicaoController',
		[ "$scope", '$state', '$q', '$timeout', 'usuarioEdicaoService', 'loginService', '$rootScope', function($scope, $state, $q, $timeout, usuarioEdicaoService, loginService,  $rootScope) {

	$scope.listaCidade = null;
	$scope.listaEstado = null;
	$scope.listaPerfil = null;
	$scope.listaCidadeFiltrada = null;

	$scope.listaSexo = [
		{descricao : "Feminino", valor : "F"},
		{descricao : "Masculino", valor : "M"}
	];

	$scope.listaTipoTelefone = [
		{descricao : "Residencial", valor : "R"},
		{descricao : "Celular", valor : "C"},
		{descricao : "Trabalho", valor : "T"}
	];

	construir();

	function construir(){
		//configurarMascara();
		recuperarUsuario();
		recuperarListaEdicao();
		if($state.params.id){
			recuperarUsuarioEdicao();
		}
	}

	function configurarMascara() {
		$(document).ready(function(){
			//$('#birth-date').mask('00/00/0000');
			$('#telefone').mask('(000)00000-0000');
		});
	}

	function recuperarUsuario() {
		loginService.confirmarUsuario($scope.dados,
			function (usuarioPerfil) {
				if (usuarioPerfil.existente == true){
					$rootScope.usuarioPerfil = usuarioPerfil;
				}else{}
			},function () {
			});
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
}]);
