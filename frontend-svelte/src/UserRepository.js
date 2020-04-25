import { writable } from 'svelte/store';

const users = writable([]);
const userLocalStorage = {
    subscribe: users.subscribe,
    setUsers: (items) => { users.set(items); },
    addUser: (user) => { users.update(items => {  return items.concat(user); });},
    deleteUser: id => { users.update(items => items.filter(current => current.id != id)) },
};

export default userLocalStorage;
