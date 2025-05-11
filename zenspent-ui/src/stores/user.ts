import {defineStore} from 'pinia';
import type {User} from "@/entities/User.ts";
import {ref} from "vue";

export const useUserStore = defineStore(
    'user',
    () =>
    {
        const user = ref<User | null>(null);

        function setUser(userData: User) {
            user.value = userData;
        }

        function clearUser() {
            user.value = null;
        }

        return {user, setUser, clearUser};
    },
    {
        persist: true
    }
    );