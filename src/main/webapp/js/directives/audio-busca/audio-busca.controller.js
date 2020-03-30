'use strict'
angular.module('tccApp').controller('AudioBuscaController',
		[ "$scope", '$state', 'audioBuscaService', '$rootScope', 'loginService', function($scope, $state, audioBuscaService, $rootScope, loginService) {

	var tipoInput = 0;
	$scope.termo = null;
	$scope.faltaTermo = false;
	synth.cancel();
	reproduzirFrase(getAudio.audioBusca.intro);
	comecarReconhecimento();

	$scope.acessarLista = function() {
		if(!$scope.termo){
			reproduzirFrase(getAudio.audioBusca.nenhumTermo);
			$scope.faltaTermo = true;
		} else {
			$state.go('audioLista', {
				termo : $scope.termo
			}, {
				reload : true
			});
		}
	};

	$scope.erroTermo = function() {
		$scope.faltaTermo = true;
	};

	document.onkeyup = function(e) {
		if (e.which == 96) {
			synth.speak(utterance);
		}
	};

	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript.trim();
				if (son == 'buscar') {
					if(!$scope.termo){
						tipoInput = 1;
						$scope.faltaTermo = true;
						document.getElementById('termo').focus();
						reproduzirFrase(getAudio.audioBusca.nenhumTermo);
					} else {
						$scope.acessarLista();
					}
				} else if (son == 'termo') {
					tipoInput = 1;
					document.getElementById('termo').focus();
					if($scope.termo){
						reproduzirFrase('O termo informado foi ' + $scope.termo + '. Informe um novo termo ou diga buscar para procurar com o termo já informado.');
					} else {
						reproduzirFrase(getAudio.audioBusca.informeTermo);
					}
				} else if (tipoInput == 1){
					$scope.termo = son;
					document.getElementById('termo').value = son;
					document.getElementById('termo').blur();
					tipoInput = 0;
					reproduzirFrase('O termo informado foi ' + son + ', diga buscar para pesquisar pelo termo informado. ' );
				} else if(son == 'menu' || son == 'voltar') {
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
					reproduzirFrase(getAudio.audioBusca.intro + ' ' + getAudio.audioBusca.fraseAjuda);
				} else if (son == 'outros') {
					reproduzirFrase(getAudio.audioBusca.fraseAjuda);
				} else {
					reproduzirFrase(getAudio.ajuda);
				}
			}
		}
	};

	$scope.logOff = function() {
		loginService.logOffUsuario();
	};

}]);
