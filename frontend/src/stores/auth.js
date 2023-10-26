import { defineStore } from 'pinia';
import { api } from 'src/boot/axios';

export const useAuthStore = defineStore({
    id: 'auth',
    state: () => ({
        user: JSON.parse(localStorage.getItem('user')) || null,
        returnUrl: null
    }),
    getters: {
        isLoggedIn: (state) => !!state.user,
    },
    actions: {
        async login(userDetails) {
            try {
                const response = await api.post(`/users/login`, {
                    email: userDetails.email,
                    password: userDetails.password
                });
                const { user, jwt } = response.data;

                this.user = user;
                localStorage.setItem('user', JSON.stringify(user));
                localStorage.setItem('jwt', jwt);
                return response
            } catch (error) {
                console.log(error);
            }
        },
        async register(userDetails) {
            try {
                const response = await api.post(`/users/register`, {
                    email: userDetails.email,
                    password: userDetails.password
                });
                const { user, jwt } = response.data;

                this.user = user;
                localStorage.setItem('user', JSON.stringify(user));
                localStorage.setItem('jwt', jwt);
                return response
            } catch (error) {
                console.log(error);
            }
        },
        async updateUser(userDetails) {
            try {
                const response = await api.put(`/users/${this.user.userId}`, {
                    email: userDetails.email,
                    password: userDetails.password
                });
                const user = response.data.user;

                localStorage.setItem('user', JSON.stringify(user));
                return response
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