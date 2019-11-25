'use strict'
angular.module('tccApp').controller('LocalizacaoController',
		[ '$scope', '$state', 'localizacaoService', '$rootScope', function($scope, $state, localizacaoService, $rootScope) {

			synth.cancel();
			reproduzirFrase(getAudio.localizacao.intro);
			comecarReconhecimento();

			document.onkeyup = function(e) {
				if (e.which == 96) {
					reproduzirFrase(getAudio.localizacao.intro);
				} else if (e.which == 97) {
					synth.cancel();
					$state.go('principal', {}, {
						reload : true
					});
				} else if (e.which == 98) {
					synth.cancel();
					$state.go('contato', {}, {
						reload : true
					});
				} else if (e.which == 99) {
					synth.cancel();
					$state.go('localizacao', {}, {
						reload : true
					});
				} else if (e.which == 100) {
					synth.cancel();
					$state.go('login', {}, {
						reload : true
					});
				} else if (e.which == 105) {
					reproduzirFrase();
				};
			};

			recognition.onresult = function(event) {
				for (let i = event.resultIndex; i < event.results.length; i++) {
					if (event.results[i].isFinal) {
						var son = event.results[i][0].transcript
								.trim();
						console.log(son);
						if (son == 'principal') {
							synth.cancel();
							$state.go('principal', {}, {
								reload : true
							});
						} else if (son == 'contato') {
							synth.cancel();
							$state.go('contato', {}, {
								reload : true
							});
						} else if (son == 'localização') {
							synth.cancel();
							$state.go('localizacao', {}, {
								reload : true
							});
						} else if (son == 'login') {
							synth.cancel();
							$state.go('login', {}, {
								reload : true
							});
						} else if (son == 'outros' || son == 'ajuda') {
							reproduzirFrase(getAudio.localizacao.fraseAjuda);
						} else if (son == 'repetir') {
							reproduzirFrase(getAudio.localizacao.intro);
						} else {
							reproduzirFrase(getAudio.ajuda);
						};
					};
				};
			};

}]);
