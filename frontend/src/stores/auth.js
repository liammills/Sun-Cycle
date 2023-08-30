import { defineStore } from 'pinia';
import { api } from 'src/boot/axios';

export const useAuthStore = defineStore({
    id: 'auth',
    state: () => ({
        user: null,
        returnUrl: null
    }),
    getters: {
        isLoggedIn: (state) => !!state.user,
    },
    actions: {
        async login(email, password) {
            try {
                const user = await api.post(`/users/login`, { email, password });
                // const user = { id: 1, email, password };
                console.log(user);

                this.user = user;

                // store user details and jwt in local storage to keep user logged in between page refreshes
                // localStorage.setItem('user', JSON.stringify(user));

                // this.router.push('/panels');
            } catch (error) {
                console.log(error);
            }
        },
        logout() {
            this.user = null;
            localStorage.removeItem('user');
            this.router.push('/');
        }
    }
});