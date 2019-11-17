'use strict'
angular.module('tccApp').service('principalService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('principal');
    };

    this.getLista = function (success, error) {
        var noticias = this.api().one('getNoticias').get().then(success, error);
        return noticias;
    };

}]);
