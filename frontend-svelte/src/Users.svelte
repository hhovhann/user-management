<script>
  import { onMount, onDestroy, beforeUpdate, afterUpdate, tick } from "svelte";
  import userRepository from "./UserRepository.js";
  // API Endpoint
  const API_ENDPOINT = `http://localhost:8080/api`;
  // Array used to iterate over number of users
  let users = [], isLoading = false, user = { id: null, name: "", email: "" };
  // Add new user
  let addUser = async () => {
    const newUser = {  id: users.length + 1, name: user.name, email: user.email };
    let response = await fetch(`${API_ENDPOINT}/users`, {
      method: 'POST',
      headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
      body: JSON.stringify(newUser),
    });
    let { status, ok } = response;
    if (status === 200 && ok === true) {
      userRepository.addUser(newUser);
      user = { id: null, name: "", email: "" };
    }
  };
  // Delete user by id
  let deleteUserById = async id => {
    let response = await fetch(`${API_ENDPOINT}/users/${id}`, {
      method: "DELETE",
      body: new FormData().append("id", id)
    });
    let { status, ok } = response;
    if (status === 200 && ok === true) {
      userRepository.deleteUser(id);
    } else {
      throw new Error(`There is no more users: ${result.text}`);
    }
  };
  onMount(async () => {
    let response = await fetch(`${API_ENDPOINT}/users`);
    let result = await response.json();
    if (response.status === 200 && response.ok === true) {
      userRepository.setUsers(result);
    } else {
      throw new Error(`There is no more users: ${result.text}`);
    }
    console.log("on mount ...");
  });

  onDestroy(() => {
    console.log("on destroy ...");
  });

  beforeUpdate(() => {
    console.log("before update ...");
  });

  afterUpdate(() => {
    console.log("after update ...");
  });

  tick(() => {
    console.log("tick ...");
  });
</script>

<section>
  <div class="container">
    <div class="row mt-5">
      <div class="col-md-6">
        <div class="card p-2 shadow">
          <div class="card-body">
            <h5 class="card-title mb-4">Add New User</h5>
            <form>
              <div class="form-group">
                <label for="name">Name</label>
                <input
                  bind:value={user.name}
                  type="text"
                  class="form-control"
                  id="name"
                  placeholder="User Name" />
              </div>
              <div class="form-group">
                <label for="email">email</label>
                <input
                  bind:value={user.email}
                  type="text"
                  class="form-control"
                  id="email"
                  placeholder="User Email" />
              </div>
              <button
                type="submit"
                on:click|preventDefault={addUser}
                class="btn btn-primary">
                Add User
              </button>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        {#each $userRepository as currentUser}
          <div class="card ">
            <div class="card-body">
              <h5 class="card-title">{currentUser.name}</h5>
              <p class="card-text">{currentUser.email}</p>
              <button class="btn btn-danger" on:click|preventDefault={() => deleteUserById(currentUser.id)}>Delete</button>
            </div>
          </div>
        {/each}
      </div>
    </div>
  </div>
</section>
