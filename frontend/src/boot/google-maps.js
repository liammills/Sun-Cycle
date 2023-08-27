import { boot } from 'quasar/wrappers';
import VueGoogleMaps from '@fawmi/vue-google-maps';

export default boot(({ app }) => {
    app.use(VueGoogleMaps, {
        load: {
            // key: process.env.GOOGLE_MAPS_API_KEY,
            key: 'AIzaSyDf3yV3ZleoZ_LcpNlJMzQ0QtQ4zJyUSo8',
        },
    });

    app.component('GMapMap', VueGoogleMaps.GMapMap);
});
