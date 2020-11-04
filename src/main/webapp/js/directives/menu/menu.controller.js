'use strict'
angular.module('tccApp').controller('MenuController',
		['$scope', '$state', 'menuService', 'loginService', '$rootScope', function($scope, $state, menuService, loginService, $rootScope) {
	
	synth.cancel();
	reproduzirFrase(getAudio.menu.intro);
	comecarReconhecimento();

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

	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript.trim();
				if (son == 'menu') {
					synth.cancel();
					$state.go('menu',{},{reload : true});
				} else if (son == 'áudio') {
					synth.cancel();
					$state.go('audioBusca',{},{reload : true});
				} else if (son == 'agenda') {
					synth.cancel();
					$state.go('agenda',{data : new Date()}, {reload : true});
				} else if (son == 'sair') {
					synth.cancel();
					$state.go('principal', {}, {reload : true});
				} else if (son == 'ajuda') {
					reproduzirFrase(getAudio.menu.intro + ' ' + getAudio.menu.fraseAjuda);
				} else if (son == 'outros') {
					reproduzirFrase(getAudio.menu.fraseAjuda);
				} else {
					reproduzirFrase(getAudio.ajuda);
				}
			}
		}
	};

	$scope.acessarAgenda = function() {
		$state.go('agenda',{data : new Date()}, {reload : true});
	};

	$scope.acessarAgendaLedor = function() {
		$state.go('aulaLedor',{data : new Date()}, {reload : true});
	};

	$scope.logOff = function() {
		loginService.logOffUsuario();
	};

}]);
