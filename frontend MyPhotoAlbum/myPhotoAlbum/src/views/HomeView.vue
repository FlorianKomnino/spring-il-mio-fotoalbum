
<script>
import TheWelcome from '../components/TheWelcome.vue';
import axios from "axios";
import FotoCard from "../components/FotoCard.vue"

export default {
  name: "HomeView",

  components: {
    TheWelcome,
    FotoCard,
  },

  data() {
    return {
      baseUrl: "http://localhost:8080/api/v1",
      fotos: [],
      message: {
        messageText: "",
        senderEmail: "",
      }
    }
  },

  methods: {
    baseAxiosGet() {
      axios.get(this.baseUrl + "/fotos", {
        params: {

        }
      })
        .then((response) => {
          console.log(response.data);
          this.fotos = response.data;
        })
        .catch(function (error) {
          console.log(error);
        })
        .finally(function () {
          // always executed
        });
    },

    messageAxiosPost() {
      axios.post(this.baseUrl + "/message", this.message)
        .then((response) => {
          console.log(response.data);
          this.$router.push('/');
        })
        .catch(function (error) {
          console.log(error);
        })
        .finally(function () {
          // always executed
        });
    },
  },

  created() {
    this.baseAxiosGet()
  }
}
</script>

<template>
  <header>
    <h1>Il tuo album di foto online</h1>
  </header>
  <main>
    <TheWelcome />
    <div class="fotoCardsContainer">
      <div class="singleFoto" v-for="foto in fotos">
        <FotoCard v-if="foto.banned == false" :foto="foto" />
      </div>
    </div>
  </main>
  <footer>
    <form>
      <label>Inserisci il testo del messaggio</label>
      <br>
      <input type="text" name="messageText" v-model="message.messageText">
      <br>
      <label>Inserisci la tua mail</label>
      <br>
      <input type="mail" name="senderEmail" v-model="message.senderEmail">
      <br>
      <input type="submit" value="Invia" @click="messageAxiosPost">
    </form>
  </footer>
</template>

<style>
header {
  width: 100%;
  height: 50px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: rgb(28, 28, 68);
}

h1 {
  width: 100%;
  text-align: center;
  color: white;
}

main {
  margin-top: 50px;
  margin-bottom: 42px;
}

footer {
  height: 200px;
  width: 100%;
  background-color: rgb(24, 24, 42);
  color: white;

}

.fotoCardsContainer {
  display: flex;
  flex-wrap: wrap;
}

.footerP {
  color: white;
  text-align: center;
}
</style>