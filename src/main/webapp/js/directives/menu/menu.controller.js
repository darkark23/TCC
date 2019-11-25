'use strict'
angular.module('tccApp').controller('MenuController',
		['$scope', '$state', 'menuService', '$rootScope', function($scope, $state, menuService, $rootScope) {
	
	synth.cancel();
	reproduzirFrase(getAudio.menu.intro);
	comecarReconhecimento();

	document.onkeyup = function(e) {
		if (e.which == 96) {
			reproduzirFrase(getAudio.menu.intro);
		} else if (e.which == 97) {
			synth.cancel();
			$state.go('audio-busca', {}, {
				reload : true
			});
		} else if (e.which == 98) {
			synth.cancel();
			$state.go('agenda', {}, {
				reload : true
			});
		} else if (e.which == 99) {
			synth.cancel();
			$state.go('principal', {}, {
				reload : true
			});
		} else if (e.which == 105) {
			reproduzirFrase(getAudio.menu.fraseAjuda);
		}
	};

	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript
						.trim();
				console.log(son);
				if (son == 'menu') {
					synth.cancel();
					$state.go('menu', {}, {
						reload : true
					});
				} else if (son == 'áudio') {
					synth.cancel();
					$state.go('audio-busca', {}, {
						reload : true
					});
				} else if (son == 'agenda') {
					synth.cancel();
					acessarAgenda();
				} else if (son == 'sair') {
					synth.cancel();
					$state.go('principal', {}, {
						reload : true
					});
				} else if (son == 'outros' || son == 'ajuda') {
					reproduzirFrase(getAudio.menu.fraseAjuda);
				} else if (son == 'repetir') {
					reproduzirFrase(getAudio.menu.intro);
				} else {
					reproduzirFrase(getAudio.ajuda);
				}
			}
		}
	};

	$scope.acessarAgenda = function() {
		$state.go('agenda', {
			data : new Date()
		}, {
			reload : true
		});
	};

}]);
