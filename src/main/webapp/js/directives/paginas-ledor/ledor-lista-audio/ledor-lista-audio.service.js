'use strict'
angular.module('tccApp').service('ledorListaAudioService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('audio');
    };

    this.findAllLedor = function (login ,success, error) {
        return this.api().one('findAllLedor').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', login).then(success, error);
    };

    this.submeter = function (audioLivros ,success, error) {
        let status = this.api().all('submeter').customPUT(audioLivros).then(success, error);
        return status;
    };

    this.reprovar = function (audioLivros ,success, error) {
        let status = this.api().all('reprovar').customPUT(audioLivros).then(success, error);
        return status;
    };

    this.aprovar = function (audioLivros ,success, error) {
        let status = this.api().all('aprovar').customPUT(audioLivros).then(success, error);
        return status;
    };

    this.remover = function (audioLivros ,success, error) {
        let status = this.api().all('remover').customPUT(audioLivros).then(success, error);
        return status;
    };

}]);
