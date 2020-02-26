'use strict'
angular.module('tccApp').service('ledorListaAudioService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('audio');
    };

    this.findAllLedor = function (login ,success, error) {
        return this.api().one('findAllLedor').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', login).then(success, error);
    };

    this.remover = function (id, success, error) {
        var status = this.api().one('remove/' + id).get().then(success, error);
        return status;
    };

}]);
