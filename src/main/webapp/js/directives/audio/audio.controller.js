'use strict'
angular.module('tccApp').controller('AudioController',
    ["$scope", '$state', 'audioService', '$rootScope', function ($scope, $state, audioService, $rootScope) {

        var audio = document.getElementById('audio');
        audio.currentTime = 12;

        $scope.salvarPosicao = function () {
            console.log(audio.currentTime);
        }

        $scope.reproduzirAudio = function () {
            audio.play();
            $scope.tempoAudio = audio.currentTime;
        }

        $scope.pararAudio = function () {
            audio.pause();
            $scope.salvarPosicao();
        }

        $scope.avancarAudio = function () {
            if (audio.currentTime + 2.5 > audio.duration) {
                audio.currentTime = audio.currentTime + 2.5;
                audio.pause();
            } else {
                audio.currentTime = audio.currentTime + 2.5;
            }
            console.log(audio.currentTime);
        }

        $scope.retrocederAudio = function () {
            if (audio.currentTime < 2.5) {
                audio.currentTime = 0;
            } else {
                audio.currentTime = audio.currentTime - 2.5;
            }
            console.log(audio.currentTime);
        }


        var frasePrincipal = 'Página de Leitura de Audio Livro. Diga reproduzir para iniciar a reprodução, diga parar para pausar a reprodução.';

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
                    if (son == 'reproduzir') {
                        synth.cancel();
                        $scope.reproduzirAudio();
                    } else if (son == 'parar') {
                        synth.cancel();
                        $scope.pararAudio();
                    } else {
                        reproduzirFrase('Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda.');
                    }
                }
            }
        };

    }]);
