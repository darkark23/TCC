'use strict'
angular.module('tccApp').controller('AudioController',
    ["$scope", '$state', 'audioService', '$rootScope', 'loginService' , function ($scope, $state, audioService, $rootScope, loginService) {

    $scope.audioLivro = null;
    $scope.audio = null;

    audioService.getAudioLivro($state.params.id,function (audioLivro) {
        $scope.audioLivro = audioLivro;
        $scope.audio = document.getElementById('audio');
    },function () {
    });

    synth.cancel();
    reproduzirFrase(getAudio.audio.intro);
    comecarReconhecimento();

    $scope.reproduzirAudio = function () {
        audio.play();
    };

    var reiniciarAudio = function () {
        audio.currentTime = 0;
    };

    $scope.pararAudio = function () {
        audio.pause();
        $scope.salvarPosicao();
    };

    $scope.salvarPosicao = function () {
        console.log(audio.currentTime);
    };

    $scope.avancarAudio = function () {
        if (audio.currentTime + 30 > audio.duration) {
            audio.currentTime = audio.currentTime + 30;
            audio.pause();
            reproduzirFrase(getAudio.audio.finalAudio)
        } else {
            audio.currentTime = audio.currentTime + 30;
        }
    };

    $scope.aumentarVolume = function () {
        if (audio.volume + 0.2 < 1) {
            audio.volume = audio.volume +0.2;
        }
    };

    $scope.abaixarVolume = function () {
        if (audio.volume - 0.2 > 0) {
            audio.volume = audio.volume - 0.2;
        }
    };

    $scope.repetirAudio = function () {
        audio.currentTime = 0;
        audio.play();
    };

    $scope.retrocederAudio = function () {
        if (audio.currentTime < 30) {
            audio.currentTime = 0;
            reproduzirFrase(getAudio.audio.inicioAudio)
        } else {
            audio.currentTime = audio.currentTime - 30;
        }
    };

    $scope.voltar = function () {
        window.history.back();
    }

    var lerInformacoes = function(){
        let titulo = 'Título: ' + $scope.audioLivro.tituloAudioBook;
        let ledor = ', Ledor: ' + $scope.audioLivro.ledor;
        let livro = ', Livro de Referência: ' + ($scope.audioLivro.tituloLivroReferencia) ? $scope.audioLivro.tituloLivroReferencia : 'Sem livro de referência.';
        let descricao = ', Descrição: ' + $scope.audioLivro.descricao;
        let assunto = ', Assuntos: ';
        if ($scope.audioLivro.listaAssunto.lenght == 0){
            assunto + 'Sem assuntos referênciados.'
        }else{
            $scope.audioLivro.listaAssunto.forEach(adicionarAssuntos);
        }
        $scope.pararAudio();
        reproduzirFrase(titulo + ledor + livro + descricao + assunto);

        function adicionarAssuntos(item, index) {
            assunto = assunto + item.nome + ' , ';
        }

    };

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
                if (son == 'reproduzir') {
                    synth.cancel();
                    $scope.reproduzirAudio();
                } else if (son == 'aumentar') {
                    synth.cancel();
                    $scope.aumentarVolume();
                } else if (son == 'abaixar') {
                    synth.cancel();
                    $scope.abaixarVolume();
                }  else if (son == 'parar') {
                    synth.cancel();
                    $scope.pararAudio();
                } else if (son == 'avançar') {
                    synth.cancel();
                    $scope.avancarAudio();
                } else if (son == 'retroceder') {
                    synth.cancel();
                    $scope.retrocederAudio();
                }  else if (son == 'informações') {
                    lerInformacoes();
                } else if (son == 'repetir') {
                    reiniciarAudio();
                }  else if(son == 'menu') {
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
                    reproduzirFrase(getAudio.audio.intro + ' ' + getAudio.audio.fraseAjuda);
                } else if (son == 'outros') {
                    reproduzirFrase(getAudio.audio.fraseAjuda);
                } else if (son == 'voltar') {
                    $scope.voltar();
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
