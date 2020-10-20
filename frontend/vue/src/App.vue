<template>
    <div id="app">
        <Header />
        <Navigation />
        <Content />
        <Footer />
    </div>
</template>

<script>
    import Header from "@/components/layouts/Header.vue";
    import Navigation from "@/components/layouts/Navigation.vue";
    import Content from "@/components/layouts/Content.vue";
    import Footer from "@/components/layouts/Footer.vue";

    export default {
        name: "App",

        components: {
            Header,
            Navigation,
            Content,
            Footer,
        },

        data() {
            return {};
        },
    };
</script>
