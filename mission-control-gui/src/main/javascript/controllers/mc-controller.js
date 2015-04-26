/**
 * The controller for mission control
 */
(function (w, mc) {

    'use strict';

    /**
     * The comment controller has its own view and rest client which are given in cfg.
     * It initializes the view's callback to handle adding comments.
     * @param cfg contains the controller's view handling jquery requests and DOM manipulation.
     *             contains the rest client triggering the database requests.
     *             contains the error controller handling ajax errors.
     * @constructor
     */
    mc.controllers.McController = function (cfg) {
        this.view = cfg.view;
        this.restClient = cfg.restClient;

        this.view.setCallback('onSwitchToConfig', $.proxy(this.switchToConfig, this));
        this.view.setCallback('onSwitchToOverview', $.proxy(this.switchToOverview, this));
        this.view.setCallback('onPostConfig', $.proxy(this.postConfiguration, this));

    };


    mc.controllers.McController.prototype.switchToConfig = function (configName) {
        var view = this.view;
        this.restClient.getConfiguration(configName)
            .done(function (data) {
                view.displayConfiguration(configName, data);
            });

    };

    mc.controllers.McController.prototype.switchToOverview = function () {
        var view = this.view;

        this.restClient.getAllConfigurations()
            .done(function (data) {
                view.displayOverview(data);
            });
    };

    mc.controllers.McController.prototype.postConfiguration = function (configName, keyValueMap) {
        var view = this.view;

        this.restClient.setConfigurationValues(configName, keyValueMap)
            .done(function (data) {
                view.displayConfiguration(configName, data);
            });
    };

}(window, mc));
