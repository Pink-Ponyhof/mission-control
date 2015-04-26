/**
 * Created by andreas.janning on 26.04.2015.
 */

/**
 * The rest client of the mission control application.
 *
 * Its methods build the correct url from the given parameters and call the desired backend method.
 */
(function ($) {

    'use strict';

    /**
     * @param cfg specifies the base url of the rest client which is used for all requests
     * and the backend performing the ajax requests.
     * @constructor
     */
    mc.backend.RestClient = function (cfg) {
        this.baseUrl = cfg.baseUrl;
        this.callBackend = cfg.callBackend;
    };

    $.extend(mc.backend.RestClient.prototype, {

        /**
         * Get the metadata of all configurations currently managed by mission control
         *
         * @returns jQuery promise of all configuration metadata
         */
        getAllConfigurations: function () {
            var callUrl = this.baseUrl + '/';
            return this.callBackend.getData(callUrl);
        },

        /**
         * Get all data of a single configuration by name
         *
         * @param name the name of the configuration to get
         * @returns jQuery promise of the configuration data
         */
        getConfiguration: function (name) {
            var callUrl = this.baseUrl + '/' + name;
            return this.callBackend.getData(callUrl);
        },

        /**
         * Replace the stored configuration values in a configuration with the given values
         *
         * @param name the name of the configuration of witch to replace the values
         * @param keyValueMap the new key/values for the configuration
         * @returns jQuery promise with the new configuration data after the modifications
         */
        setConfigurationValues: function (name, keyValueMap) {
            var callUrl = this.baseUrl + '/' + name;
            return this.callBackend.postData(callUrl, keyValueMap);
        }
    });

}(jQuery));
