import VueGoogleMaps from '@fawmi/vue-google-maps';

export default ({ app }) => {
    app.use(VueGoogleMaps, {
        load: {
<<<<<<< HEAD
            key: 'AIzaSyDf3yV3ZleoZ_LcpNlJMzQ0QtQ4zJyUSo8',
=======
            key: process.env.GOOGLE_MAPS_API_KEY,
>>>>>>> c59306d (Last minute commit)
        },
    });
};
