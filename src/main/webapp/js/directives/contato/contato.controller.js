'use strict'
angular.module('tccApp').controller('ContatoController',
		[ "$scope", '$state', 'contatoService', '$rootScope', function($scope, $state, contatoService, $rootScope) {


			var frasePrincipal = 'Página de contato. O telefone para contato é 61 3901 7607.';
			
			synth.cancel();
			reproduzirFrase(frasePrincipal);

			comecarReconhecimento();

			document.onkeyup = function(e) {
				if (e.which == 96) {
					reproduzirFrase(frasePrincipal);
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
					reproduzirFrase('Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.');
				}
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
							reproduzirFrase('Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.');
						} else if (son == 'repetir') {
							reproduzirFrase(frasePrincipal);
						} else {
							reproduzirFrase('Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda.');
						}
					}
				}
			};

		}]);
