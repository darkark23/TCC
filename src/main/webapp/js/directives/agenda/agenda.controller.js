'use strict';
angular.module('tccApp').controller('AgendaController',
		[ "$scope", '$state', 'agendaService', '$rootScope','loginService', function($scope, $state, agendaService, $rootScope, loginService) {

	var audioAgenda = null;

	comecarReconhecimento();

	if ($state.params.data){
		agendaService.getAgendaDia($state.params.data,function (agendaDia) {
			$scope.agendaDia = agendaDia;
			if($state.params.data){
				$scope.dataSelecionada = new Date($state.params.data);
			}else{
				$scope.dataSelecionada = new Date();
			}
			construirAudioAgenda();
			iniciarAudio();
		},function () {
		})
	}else {
		agendaService.getAgendaDia(new Date(),function (agendaDia) {
			$scope.agendaDia = agendaDia;
			$scope.dataSelecionada = new Date();
			construirAudioAgenda();
			iniciarAudio();
		},function () {
		})
	}

	document.onkeyup = function(e) {
		if (e.which == 96) {
			synth.speak(utterance);
		}
	};

	recognition.onresult = function(event) {
		for (let i = event.resultIndex; i < event.results.length; i++) {
			if (event.results[i].isFinal) {
				var son = event.results[i][0].transcript.trim();
				if(son == 'próximo'){
					synth.cancel();
					$scope.acessarAgendaProxima();
				} else if(son == 'anterior'){
					synth.cancel();
					$scope.acessarAgendaAnterior();
				} else if(son == 'repetir'){
					synth.cancel();
					reproduzirFrase(audioAgenda)
				} else if (son == 'voltar' || son == 'menu'){
					synth.cancel();
					$state.go('menu',{}, {reload : true});
				} else if (son == 'áudio'  || son == 'áudio livro' ) {
					synth.cancel();
					$state.go('audioBusca',{},{reload : true});
				} else if (son == 'agenda') {
					synth.cancel();
					$state.go('agenda',{data : new Date()}, {reload : true});
				} else if (son == 'sair') {
					synth.cancel();
					$state.go('principal', {}, {reload : true});
				} else if (son == 'ajuda') {
					reproduzirFrase(getAudio.agenda.intro + '' + getAudio.agenda.comandos + ' ' + getAudio.agenda.fraseAjuda);
				} else if (son == 'outros') {
					reproduzirFrase(getAudio.agenda.fraseAjuda);
				} else {
					reproduzirFrase(getAudio.ajuda);
				}
			}
		}
	};

	$scope.acessarAgendaProxima = function() {
		let proximaData;
		if($state.params.data){
			proximaData = new Date($state.params.data);
		}else{
			proximaData = new Date();
		}

		proximaData.setDate(proximaData.getDate() + 1);
		$state.go('agenda',{data : proximaData},{reload : true});
	};

	$scope.acessarAgendaAnterior = function() {
		let proximaData;
		if($state.params.data){
			proximaData = new Date($state.params.data);
		}else{
			proximaData = new Date();
		}

		proximaData.setDate(proximaData.getDate() - 1);
		$state.go('agenda',{data : proximaData},{reload : true});
	};

	$scope.acessarAgendaBusca = function() {
		let proximaData = new Date($scope.dataSelecionada);
		$state.go('agenda',{data : proximaData},{reload : true});
	};

	function construirAudioAgenda (){
		if($scope.agendaDia.listaAulas.length == 0){
			audioAgenda = ' Agenda para o dia ' + $scope.agendaDia.data + '. não existem aulas marcadas no momento.'
		}else {
			audioAgenda = ' Agenda para o dia ' + $scope.agendaDia.data + '. Para este dia estão marcadas as seguintes aulas: ';
			$scope.agendaDia.listaAulas.forEach(adicionarAulasAudio);
		}
	}

	function adicionarAulasAudio(item, index) {
		audioAgenda = audioAgenda + item.descricao + ' , ';
	}

	function iniciarAudio() {
		synth.cancel();
		reproduzirFrase(audioAgenda + getAudio.agenda.comandos);
	}

	$scope.logOff = function() {
		loginService.logOffUsuario();
	};
	          
}]);
