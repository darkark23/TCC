'use strict'
angular.module('tccApp').service('ledorEditarAudioService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('audio');
    };

    this.findByIdEdicao = function (id, success, error) {
        var audioLivro = this.api().one('findByIdEdicao/' + id).get().then(success, error);
        return audioLivro;
    };

    this.getListaAudioEdicao = function (success, error) {
        var lista = this.api().one('getListaAudioEdicao').get().then(success, error);
        return lista;
    }

    //this.salvarAudioLivro = function (audioLivros ,success, error) {
//        return this.api().one('salvarAudioLivro').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', audioLivros).then(success, error);
//    };

    this.salvarAudioLivro = function (audioLivros ,success, error) {
        return Restangular.all('audio/salvarAudioLivro').post(audioLivros).then(success, error);
    };

}]);
