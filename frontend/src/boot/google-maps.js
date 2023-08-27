import VueGoogleMaps from '@fawmi/vue-google-maps';

export default ({ app }) => {
    app.use(VueGoogleMaps, {
        load: {
            key: 'YOUR_API_KEY_COMES_HERE',
        },
    });
};
