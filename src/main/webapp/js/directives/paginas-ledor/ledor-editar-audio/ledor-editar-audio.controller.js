'use strict'
angular.module('tccApp').controller('LedorEditarAudioController',
		[ "$scope", '$state', '$q', '$timeout', 'ledorEditarAudioService', 'loginService', '$rootScope', function($scope, $state, $q, $timeout, ledorEditarAudioService, loginService,  $rootScope) {

	construir();
	$scope.audioLivro = null;
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
		}

		ledorEditarAudioService.getListaAudioEdicao(function (lista) {
			$scope.listaAssunto = lista.listaAssuntoSelecao;
			$scope.listaLivro = lista.listaLivroSelecao;
		},function () {
		});
	}

	$scope.salvarLivro = function() {
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
	};

	$scope.acessarAudio = function(id) {
		$state.go('audio',{id : id}, {reload : true});
	};

	$scope.voltar = function() {
		$state.go('audioListaLedor',{}, {reload : true});
	};

}]);
