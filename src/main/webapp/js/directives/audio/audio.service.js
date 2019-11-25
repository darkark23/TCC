'use strict'
angular.module('tccApp').service('audioService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('audio');
    };

    this.getAudioLivro = function (id, success, error) {
        var audioLivro = this.api().one('findById/' + id).get().then(success, error);
        return audioLivro;
    };

}]);
