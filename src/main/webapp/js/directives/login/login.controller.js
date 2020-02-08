'use strict'
angular.module('tccApp').controller('LoginController',
		[ '$scope', '$state', 'loginService', '$rootScope', function($scope, $state, loginService, $rootScope) {

	$scope.dados = {usuario : null,	senha : null};
	var tipoInput = 0;

	synth.cancel();
	reproduzirFrase(getAudio.login.intro);
	comecarReconhecimento();

	$scope.confirmarUsuario = function(){
		loginService.confirmarUsuario($scope.dados,
			function (usuarioPerfil) {
				if (usuarioPerfil.existente == true){
					synth.cancel();
					$rootScope.usuarioPerfil = usuarioPerfil;
					$state.go('menu',{},{reload : true});

				}else{
					reproduzirFrase(getAudio.login.usuarioSenhaIncorreto);
				}
			},function () {
		});
	};

	document.onkeyup = function(e) {
		if (e.which == 96) {
			reproduzirFrase(getAudio.login.intro + getAudio.login.fraseAjuda);
		} else if (e.which == 49) {
			synth.cancel();
			$state.go('principal',{},{reload : true});
		} else if (e.which == 50) {
			synth.cancel();
			$state.go('contato',{},{reload : true});
		} else if (e.which == 51) {
			synth.cancel();
			$state.go('localizacao',{}, {reload : true});
		} else if (e.which == 52) {
			synth.cancel();
			$state.go('login',{}, {reload : true});
		}
	};

	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript
						.trim();
				if (son == 'entrar') {
					if(!$scope.dados.usuario){
						reproduzirFrase(getAudio.login.informeUsuario);
						tipoInput = 1;
					} else if(!$scope.dados.senha){
						reproduzirFrase(getAudio.login.informeSenha);
						tipoInput = 2;
					} else if(!$scope.dados.usuario && !$scope.dados.senha){
						reproduzirFrase(getAudio.login.informeUsuarioSenha);
						tipoInput = 0;
					} else {
						$scope.confirmarUsuario();
						tipoInput = 0;
					}
				}else if (son == 'usuário') {
					if($scope.dados.usuario){
						document.getElementById('usuario').focus();
						tipoInput = 1;
						reproduzirFrase('O usuário digitado foi ' + $scope.dados.usuario + '. Informe um novo usuário.');
					} else {
						document.getElementById('usuario').focus();
						tipoInput = 1;
						reproduzirFrase(getAudio.login.informeUsuario);
					}
				} else if (son == 'senha') {
					if($scope.dados.senha){
						document.getElementById('senha').focus();
						tipoInput = 2;
						reproduzirFrase('A senha informada foi ' + $scope.dados.senha + '. Informe uma nova senha.');
					} else {
						document.getElementById('senha').focus();
						tipoInput = 2;
						reproduzirFrase(getAudio.login.informeSenha);
					}
				} else if (tipoInput == 1){
					$scope.dados.usuario = son;
					document.getElementById('usuario').value = son;
					document.getElementById('usuario').blur();
					tipoInput = 0;
					reproduzirFrase('O usuário digitado foi ' + son);
				} else if (tipoInput == 2){
					document.getElementById('senha').blur();
					document.getElementById('senha').value = son;
					$scope.dados.senha = son;
					tipoInput = 0;
					reproduzirFrase('A senha digitada foi ' + son);
				}else if (son == 'principal' && tipoInput == 0) {
					synth.cancel();
					$state.go('principal',{},{reload : true});
				} else if (son == 'contato' && tipoInput == 0) {
					synth.cancel();
					$state.go('contato',{},{reload : true});
				} else if (son == 'localização' && tipoInput == 0) {
					synth.cancel();
					$state.go('localizacao',{},{reload : true});
				} else if (son == 'login' && tipoInput == 0) {
					synth.cancel();
					$state.go('login',{},{reload : true});
				} else if (son == 'outros' && tipoInput == 0) {
					reproduzirFrase(getAudio.login.fraseAjuda);
				} else if (son == 'ajuda' && tipoInput == 0) {
					reproduzirFrase(getAudio.login.intro + getAudio.login.fraseAjuda);
				} else {
					reproduzirFrase(getAudio.ajuda);
				}
			}
		}
	};

}]);
