import axios from 'axios';
import { Notify } from 'quasar';

// Be careful when using SSR for cross-request state pollution
// due to creating a Singleton instance here;
// If any client changes this (global) instance, it might be a
// good idea to move this instance creation inside of the
// "export default () => {}" function below (which runs individually
// for each client)
const api = axios.create({ 
  baseURL: 'http://localhost:8000',
  headers: {'Content-Type': 'application/json'}
})

async function handleErrors({ error, router }) {
  if (error.response.status === 401) {
    // if (router.currentRoute.path !== '/login') {
    //   router.replace({
    //     path: '/login',
    //     query: {
    //       redirect: router.currentRoute.fullPath,
    //     },
    //   });
    // }

    Notify.create({
      message: 'Session expired, please retry login',
      type: 'primary',
      icon: 'error',
      position: 'bottom',
    });
    return error;
  }

  return Promise.reject(error);
}

export default async({ app, router }) => {
  const token = await localStorage.getItem('user');
  if (!!localStorage.getItem('user') && token) {
    api.defaults.headers.common.Authorization = `Bearer ${token}`;
  }
  console.log('axios.js', api.defaults.headers.common.Authorization);

  // api.interceptors.response.use(
  //   response => response,
  //   error => handleErrors({ error, router }),
  // );

  app.config.globalProperties.$axios = axios
  app.config.globalProperties.$api = api
}

export { api }
