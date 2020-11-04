'use strict'
angular.module('tccApp').controller('LedorListaAudioController',
		[ "$scope", '$state', 'ledorListaAudioService', 'loginService', '$rootScope', function($scope, $state, ledorListaAudioService, loginService,  $rootScope) {

	var construir = function (){
		loginService.confirmarUsuario($scope.dados,
			function (usuarioPerfil) {
				if (usuarioPerfil.existente == true){
					synth.cancel();
					$rootScope.usuarioPerfil = usuarioPerfil;
					carregarListaLivros($rootScope.usuarioPerfil.login);
				}else{}
			},function () {
			});
	}

	synth.cancel();
	construir();

	$scope.audioLivros = null;
	$scope.tipoOperacao = {
		APROVAR:1,
		REPROVAR:2,
		REMOVER:3,
		SUBMETER:4
	}

	var currentResultado = 0;
	$scope.modal = {
		id:"",
		mensagem:"",
		titulo:"",
		justificativa:"",
		indicadorMostrarjustificativa:false,
		tipoOperacao:""
	}



	$scope.acessarAudio = function(id) {
		$state.go('audio',{id : id}, {reload : true});
	};

	$scope.voltar = function() {
		$state.go('menu',{}, {reload : true});
	};

	var reproduzirIntro = function() {
		if(!$scope.audioLivros || $scope.audioLivros.length == 0 ){
			reproduzirFrase(getAudio.audioLista.intro + ' ' + getAudio.audioLista.naoEncontrado);
		}else {
			if($scope.audioLivros.length == 1){
				reproduzirFrase(getAudio.audioLista.intro + ' Foi encontrado um resultado.' );
			}else {
				reproduzirFrase(getAudio.audioLista.intro + ' Foram encontrados ' + $scope.audioLivros.length + ' resultados. ' + getAudio.audioLista.comandos );
			}
		}
	};

	function carregarListaLivros(login) {
		let dados = {usuario : null};
		dados.usuario = login;
		ledorListaAudioService.findAllLedor(dados,
			function (lista) {
				$scope.audioLivros = lista;
			},function () {
			})
	}

	$scope.remover = function(id) {
		var dto = {id:id,controle:{descricaoReprovado:""}};
		ledorListaAudioService.remover(dto,function (status) {
			if(status == 1){
				console.log('Removido com sucesso!')
				construir();
			}else {
				console.log('Erro ao remover o audio livro!')
				construir();
			}
		},function () {
			console.log('Erro ao remover o audio livro!')
			construir();
		})
	}

	$scope.aprovar = function(id) {
		var dto = {id:id,controle:{descricaoReprovado:""}};
		ledorListaAudioService.aprovar(dto,function (status) {
			if(status == 1){
				console.log('Aprovado com sucesso!')
				construir();
			}else {
				console.log('Erro ao aprovado o audio livro!')
				construir();
			}
		},function () {
			console.log('Erro ao aprovado o audio livro!')
			construir();
		})
	}

	$scope.submeter = function(id) {
		var dto = {id:id,controle:{descricaoReprovado:""}};
		ledorListaAudioService.submeter(dto,function (status) {
			if(status == 1){
				console.log('Submetido com sucesso!')
				construir();
			}else {
				console.log('Erro ao submeter o audio livro!')
				construir();
			}
		},function () {
			console.log('Erro ao submeter o audio livro!')
			construir();
		})
	}

	$scope.reprovar = function(id) {
		var dto = {id:id,controle:{descricaoReprovado : $scope.modal.justificativa}};
		ledorListaAudioService.reprovar(dto,function (status) {
			if(status == 1){
				console.log('Reprovado com sucesso!')
				construir();
			}else {
				console.log('Erro ao reprovar o audio livro!')
				construir();
			}
		},function () {
			console.log('Erro ao reprovar o audio livro!')
			construir();
		})
	}

	$scope.mostrarAprovar = function(audio){
		return ($rootScope.usuarioPerfil.perfil == 'Professor' || $rootScope.usuarioPerfil.perfil == 'Administrador') && audio.idSituacaoAprovacao != enumSituacaoAprovacao.APROVADO && audio.idSituacaoAprovacao != enumSituacaoAprovacao.PENDENTE;
	}

	$scope.mostrarReprovar = function(audio){
		return ($rootScope.usuarioPerfil.perfil == 'Professor' || $rootScope.usuarioPerfil.perfil == 'Administrador') && audio.idSituacaoAprovacao != enumSituacaoAprovacao.REPROVADO && audio.idSituacaoAprovacao != enumSituacaoAprovacao.PENDENTE;
	}

	$scope.mostrarSubmeter = function(audio){
		return audio.idSituacaoAprovacao != enumSituacaoAprovacao.APROVADO && audio.idSituacaoAprovacao != enumSituacaoAprovacao.SUBMETIDO;
	}

	$scope.mostrarEditar = function(aula){
		return aula.idSituacaoAprovacao != enumSituacaoAprovacao.SUBMETIDO;
	}

	$scope.adicionar = function() {
		$state.go('audioEditarLedor', {}, {reload : true});
	}

	$scope.editar = function(id) {
		$state.go('audioEditarLedor',{id : id}, {reload : true});
	};

	$scope.logOff = function() {
		loginService.logOffUsuario();
	};

	$scope.mostrarModal = function(id,tipoOperacao) {
		$scope.modal.id = id;
		if(tipoOperacao == $scope.tipoOperacao.REPROVAR){
			$scope.modal.titulo = "Reprovar";
			$scope.modal.mensagem = "Reprovar o audio selecionado?";
		}else if(tipoOperacao == $scope.tipoOperacao.APROVAR){
			$scope.modal.titulo = "Aprovar";
			$scope.modal.mensagem = "Aprovar o audio selecionado?";
		}else if(tipoOperacao == $scope.tipoOperacao.SUBMETER){
			$scope.modal.titulo = "Submeter";
			$scope.modal.mensagem = "Submeter o audio selecionado para aprovação?";
		}else{
			$scope.modal.titulo = "Reprovar";
			$scope.modal.mensagem = "Reprovar o audio selecionado?";
		}
		$scope.modal.tipoOperacao = tipoOperacao;
		$scope.modal.justificativa = "";
		$scope.modal.indicadorMostrarjustificativa = tipoOperacao == $scope.tipoOperacao.REPROVAR ? true : false;
	}

	$scope.modalSim = function() {
		if($scope.modal.tipoOperacao == $scope.tipoOperacao.REPROVAR){
			$scope.reprovar($scope.modal.id);
		}else if($scope.modal.tipoOperacao == $scope.tipoOperacao.APROVAR){
			$scope.aprovar($scope.modal.id);
		}else if($scope.modal.tipoOperacao == $scope.tipoOperacao.SUBMETER){
			$scope.submeter($scope.modal.id);
		}else{
			$scope.remover($scope.modal.id);
		}
	}

	$scope.formatarSituacao = function(id) {
		let nome = formatarIdSituacaoAprovacao(id);
		return nome;
	}

}]);
