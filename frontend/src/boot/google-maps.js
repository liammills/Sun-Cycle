import VueGoogleMaps from '@fawmi/vue-google-maps';

export default ({ app }) => {
    app.use(VueGoogleMaps, {
        load: {
            key: 'AIzaSyDf3yV3ZleoZ_LcpNlJMzQ0QtQ4zJyUSo8',
        },
    });
};
