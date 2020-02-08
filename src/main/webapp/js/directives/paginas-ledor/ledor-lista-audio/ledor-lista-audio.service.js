'use strict'
angular.module('tccApp').service('ledorListaAudioService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('audioLista');
    };

    this.getListaAudioLivro = function (success, error) {
        var listaAudioLivro = this.api().one('findAll').get().then(success, error);
        return listaAudioLivro;
    };

    this.getListaAudioLivroTermo = function (termo, success, error) {
        var listaAudioLivro = this.api().one('findAll/' + termo).get().then(success, error);
        return listaAudioLivro;
    };

}]);