'use strict'
angular.module('tccApp').controller('MenuController',
		['$scope', '$state', 'menuService', '$rootScope', function($scope, $state, menuService, $rootScope) {
	
	synth.cancel();
	reproduzirFrase(getAudio.menu.intro);
	comecarReconhecimento();

	document.onkeyup = function(e) {
		if (e.which == 96) {
			reproduzirFrase(getAudio.menu.intro);
		} else if (e.which == 49) {
			synth.cancel();
			$state.go('audio-busca',{}, {reload : true});
		} else if (e.which == 50) {
			synth.cancel();
			$state.go('agenda',{data : new Date()}, {reload : true});
		} else if (e.which == 51) {
			synth.cancel();
			$state.go('principal',{}, {reload : true});
		} else if (e.which == 52) {
			reproduzirFrase(getAudio.menu.fraseAjuda);
		}
	};

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

}]);
