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
        }

        $scope.pararAudio = function () {
            audio.pause();
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

    }]);
