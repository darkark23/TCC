'use strict'
angular.module('tccApp').controller('LedorEditarAudioController',
		[ "$scope", '$state', '$q', '$timeout', 'ledorEditarAudioService', 'loginService', '$rootScope', function($scope, $state, $q, $timeout, ledorEditarAudioService, loginService,  $rootScope) {

	$scope.erro =  {
		texto:null,
		show:false
	}
	synth.cancel();
	construir();

	function construir(){
		loginService.confirmarUsuario($scope.dados,
			function (usuarioPerfil) {
				if (usuarioPerfil.existente == true){
					$rootScope.usuarioPerfil = usuarioPerfil;
				}else{}
			},function () {
			});

		if($state.params.id){
			ledorEditarAudioService.findByIdEdicao($state.params.id,function (audioLivro) {
				$scope.edicao = true;
				$scope.audioLivro = audioLivro;
			},function () {
			});
		} else {
			$scope.audioLivro = {
				tituloAudioBook:null,
				livro:null,
				url:null,
				descricao:null
			};
		}

		ledorEditarAudioService.getListaAudioEdicao(function (lista) {
			$scope.listaAssunto = lista.listaAssuntoSelecao;
			$scope.listaLivro = lista.listaLivroSelecao;
		},function () {
		});
	}

	$scope.salvarLivro = function() {
		if(verificarCampos()){
			$scope.audioLivro.ledor = $rootScope.usuarioPerfil.login;
			ledorEditarAudioService.salvarAudioLivro($scope.audioLivro,
				function (retorno) {
					if(retorno == 0){
						console.log("Audio livro salvo com sucesso!");
						$state.go('audioListaLedor',{}, {reload : true});
					}else {
						console.log("Erro na gravação do audio livro!");
						$state.go('audioListaLedor',{}, {reload : true});
					}
				},function () {
					console.log("Erro na gravação do audio livro!");
					$state.go('audioListaLedor',{}, {reload : true});
				});
		} else {
			mostrarErro();
		}
	};

	$scope.acessarAudio = function(id) {
		$state.go('audio',{id : id}, {reload : true});
	};

	$scope.voltar = function() {
		$state.go('audioListaLedor',{}, {reload : true});
	};

	$scope.logOff = function() {
		loginService.logOffUsuario();
	};

	$scope.mostrarCodigo = function() {
		return ($rootScope.usuarioPerfil.perfil == 'Professor' || $rootScope.usuarioPerfil.perfil == 'Administrador');
	};

	function verificarCampos() {
		if(!$scope.audioLivro.tituloAudioBook){
			$scope.erro.texto = "Campo título obrigatório!";
			return false;
		} else if (!$scope.audioLivro.livro) {
			$scope.erro.texto = "Campo livro referência obrigatório!";
			return false;
		} else if (!$scope.audioLivro.url){
			$scope.erro.texto = "Campo Url do Arquivo do Áudio Livro obrigatório!";
			return false;
		} else if (!$scope.audioLivro.descricao){
			$scope.erro.texto = "Campo descrição obrigatório!";
			return false;
		}
		return true;
	}

	function mostrarErro () {
		$scope.erro.show = true;
		window.scrollTo(500, 0);
	}

}]);
