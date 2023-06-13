<script>
export default {
    name: "FotoCard",

    data() {
        return {

        }
    },

    props: {
        foto: {}
    }
}
</script>

<template>
    <div class="customCard">
        <h5>
            {{ foto.title }}
        </h5>
        <figure>
            <img :src="foto.url" alt="foto pic" class="imgDimensioning">
        </figure>
        <p>
            {{ foto.description }}
        </p>
        <p>
            Categorie:
        </p>
        <ul>
            <li v-for="category in foto.categories">
                {{ category.name }}
            </li>
        </ul>
    </div>
</template>

<style scoped>
.customCard {
    height: 250px;
    width: 180px;
    border: 1px solid grey;
    margin: 10px 5px 10px 5px;

}

.imgDimensioning {
    width: 100%;
    height: 100%;
}
</style>