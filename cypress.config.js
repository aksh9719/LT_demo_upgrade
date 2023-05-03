const { defineConfig } = require("cypress");
const getCompareSnapshotsPlugin = require('cypress-image-diff-js/dist/plugin');

module.exports = defineConfig({
  "env": {
    "preserveOriginalScreenshot": true,
    "supportFile": false,
  },

  defaultCommandTimeout: 20000,
  responseTimeout: 20000,
  requestTimeout: 20000,
  reporter: 'cypress-mochawesome-reporter',
  reporterOptions: {
    "reportDir": "cypress/reports",
    "charts": true,
    "reportPageTitle": "GJG-Cypress",
    "reportFilename": "GJG_Report",
    "embeddedScreenshots": true,
    "inlineAssets": true,
    "saveAllAttempts": true,
  },
  e2e: {
    setupNodeEvents(on, config) {
      getCompareSnapshotsPlugin(on, config);
      require('cypress-mochawesome-reporter/plugin')(on);
    },
    supportFile : false,
  },
});


require('@applitools/eyes-cypress')(module);
