'use strict'
angular.module('tccApp').controller('ContatoController',
		[ '$scope', '$state', function($scope, $state) {

	synth.cancel();
	reproduzirFrase(getAudio.contato.intro);
	comecarReconhecimento();

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
				var son = event.results[i][0].transcript.trim();
				if (son == 'repetir') {
					reproduzirFrase(getAudio.contato.intro);
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
					reproduzirFrase(getAudio.contato.fraseAjuda);
				} else if (son == 'ajuda') {
					reproduzirFrase(getAudio.contato.intro + getAudio.contato.fraseAjuda);
				} else {
					reproduzirFrase(getAudio.ajuda);
				};
			};
		};
	};

}]);
