'use strict'
angular.module('tccApp').controller('LocalizacaoController',
		[ '$scope', '$state', 'localizacaoService', 'loginService', '$rootScope', function($scope, $state, localizacaoService,loginService, $rootScope) {

			synth.cancel();
			reproduzirFrase(getAudio.localizacao.intro);
			comecarReconhecimento();

			$scope.logar = function(){
				let dados = {usuario : null,	senha : null};
				loginService.confirmarUsuario(dados,
					function (usuarioPerfil) {
						if (usuarioPerfil.existente == true){
							synth.cancel();
							$rootScope.usuarioPerfil = usuarioPerfil;
							$state.go('menu',{},{reload : true});
						}else{
							$state.go('login', {}, {
								reload : true
							});
						}
					},function () {
					});
			}

			recognition.onresult = function(event) {
				for (let i = event.resultIndex; i < event.results.length; i++) {
					if (event.results[i].isFinal) {
						var son = event.results[i][0].transcript.trim();
						if (son == 'repetir') {
							reproduzirFrase(getAudio.localizacao.intro);
						} else if (son == 'principal') {
							synth.cancel();
							$state.go('principal',{},{reload : true});
						} else if (son == 'contato') {
							synth.cancel();
							$state.go('contato',{},{reload : true});
						} else if (son == 'localização') {
							synth.cancel();
							$state.go('localizacao',{},{reload : true});
						} else if (son == 'login') {
							synth.cancel();
							$state.go('login',{},{reload : true});
						} else if (son == 'outros') {
							reproduzirFrase(getAudio.localizacao.fraseAjuda);
						} else if (son == 'ajuda') {
							reproduzirFrase(getAudio.localizacao.intro + getAudio.localizacao.fraseAjuda);
						} else {
							reproduzirFrase(getAudio.ajuda);
						};
					};
				};
			};

}]);
