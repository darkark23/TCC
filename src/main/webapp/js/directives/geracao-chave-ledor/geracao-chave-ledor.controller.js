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


	$scope.voltar = function() {
		$state.go('menu',{}, {reload : true});
	};

	$scope.gerarChave = function() {
		$state.go('menu',{}, {reload : true});
	};
}]);
