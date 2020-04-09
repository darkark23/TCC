'use strict'
angular.module('tccApp').controller('GeracaoChaveLedorController',
		[ "$scope", '$state', '$rootScope', 'geracaoChaveLedorService', 'loginService', function($scope, $state, $rootScope, geracaoChaveLedorService, loginService) {

	loginService.confirmarUsuario($scope.dados,
		function (usuarioPerfil) {
			if (usuarioPerfil.existente == true){
				$rootScope.usuarioPerfil = usuarioPerfil;
				$scope.confirmarUsuario = usuarioPerfil;
			}else{
				reproduzirFrase(getAudio.login.usuarioSenhaIncorreto);
			}
		},function () {
		});

	$scope.chaveGerada = "_ _ _ _ _";
	$scope.isChaveGerada = false;

	$scope.voltar = function() {
		$state.go('menu',{}, {reload : true});
	};

	$scope.gerarChave = function() {
		geracaoChaveLedorService.gerarChaveCadastro(function (chave) {
			$scope.chaveGerada = chave;
			$scope.isChaveGerada = true;
		},function () {
			$scope.chaveGerada = "ERRO NA GERAÇÃO DA CHAVE"
			$scope.isChaveGerada = false;
		})
	};
}]);
