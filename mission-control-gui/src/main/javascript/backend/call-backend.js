/**
 * Created by andreas.janning on 26.04.2015.
 */

(function ($) {

    'use strict';

    mc.backend.CallBackend = function () {
    };

    $.extend(mc.backend.CallBackend.prototype, {
        /**
         * Performs ajax call of type get with given url
         * @param callUrl given url
         * @returns ajax response
         */
        getData: function (callUrl) {
            return $.ajax({
                type: 'GET',
                dataType: 'json',
                url: callUrl
            });
        },
        /**
         * Performs ajax call of type put with given url and data to put
         * @param callUrl given url
         * @param newData data to put
         * @returns ajax response
         */
        putData: function (callUrl, newData) {
            return $.ajax({
                type: 'PUT',
                dataType: 'json',
                contentType: 'application/json',
                url: callUrl,
                data: JSON.stringify(newData)
            });
        },

        /**
         * Performs ajax call of type put with given url and data to post
         * @param callUrl given url
         * @param newData data to post
         * @returns ajax response
         */
        postData: function (callUrl, newData) {
            return $.ajax({
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                url: callUrl,
                data: JSON.stringify(newData)
            });
        },

        /**
         * Performs ajax call of type delete with given url
         * @param callUrl given url
         * @returns ajax response
         */
        deleteData: function (callUrl) {
            return $.ajax({
                type: 'DELETE',
                url: callUrl
            });
        }
    });


}(jQuery));
