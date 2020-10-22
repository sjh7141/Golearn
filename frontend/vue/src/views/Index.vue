<template>
<div>
    <v-app id="inspire">
        <v-carousel hide-delimiters hide-delimiter-background :show-arrows="false" :cycle="cycle" height="400">
            <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src">
                <v-jumbotron dark>
                    <v-container fill-height>
                        <v-layout align-top>
                            <v-flex>
                                <h3 class="display-2" style="color:rgb(233,233,233);">{{item.title}}</h3>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-jumbotron>
            </v-carousel-item>
        </v-carousel>
        <div class="container">
            <v-container>
                <v-row>
                    <template v-for="i in cards">
                        <v-col :key="i" cols="12" sm="6" md="4" lg="3" xl="3" align="center">
                            <!-- <router-link :to="'/post/detail?post_id=' + posts[i-1].post_id"> -->
                            <div @click="goDetail(i)">
                                <v-card :elevation="0" height="300" style="position:relative; min-width:250px;">
                                    <v-img :src="i.thumbnail" class="white--text align-end" gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)" width="100%" height="210px">
                                        <!-- <v-card-title>
                                            수강인원 {{i.viewer}}명
                                        </v-card-title> -->
                                    </v-img>
                                    <v-card-actions style="width:100%; display:flex; justify-content:space-between; height:90px;">
                                        <v-row>
                                            <v-col class="card-col text-left" cols="2">
                                                <v-avatar size="36" color="grey">
                                                    <v-icon color="white">
                                                        mdi-account
                                                    </v-icon>
                                                </v-avatar>
                                            </v-col>
                                            <v-col class="card-col" cols="10">
                                                <v-row style="width:100%; font-weight:bold">
                                                    <span class="link">{{i.title}}</span>
                                                </v-row>
                                                <v-row style="width:100%">
                                                    <span class="link">{{i.nickname}}({{i.id}})</span>
                                                </v-row>
                                                <v-row style="width:100%;">
                                                    <v-chip class="mr-1" v-for="(tag,i) in i.tags" :key="i" small>{{tag}}</v-chip>
                                                </v-row>

                                            </v-col>
                                        </v-row>
                                    </v-card-actions>
                                </v-card>
                            </div>
                        </v-col>
                    </template>
                </v-row>
            </v-container>
        </div>
    </v-app>
</div>
</template>

<script>
export default {
    data() {
        return {
            cycle: true,
            items: [{
                    title: "go learn, go run",
                    src: 'https://www.udacity.com/www-proxy/contentful/assets/2y9b3o528xhq/4QTUWa3i4VL7CMc6MjXJe0/556a951cc5ff56725c4f82d0654fe9fc/HomepageBanner_tablet.jpg',
                },

            ],
            cards: [{
                    title: "C++",
                    viewer: 50,
                    like: 1000,
                    thumbnail: "https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg",
                    tags: ["C", "C++"],
                    nickname: "고런",
                    id: "golearn",
                },
                {
                    title: "C++",
                    viewer: 50,
                    like: 1000,
                    thumbnail: "https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg",
                    tags: ["C", "C++"],
                    nickname: "고런",
                    id: "golearn",
                },
                {
                    title: "C++",
                    viewer: 50,
                    like: 1000,
                    thumbnail: "https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg",
                    tags: ["C", "C++"],
                    nickname: "고런",
                    id: "golearn",
                },
                {
                    title: "C++",
                    viewer: 50,
                    like: 1000,
                    thumbnail: "https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg",
                    tags: ["C", "C++"],
                    nickname: "고런",
                    id: "golearn",
                },
                {
                    title: "C++",
                    viewer: 50,
                    like: 1000,
                    thumbnail: "https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg",
                    tags: ["C", "C++"],
                    nickname: "고런",
                    id: "golearn",
                },
                {
                    title: "C++",
                    viewer: 50,
                    like: 1000,
                    thumbnail: "https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg",
                    tags: ["C", "C++"],
                    nickname: "고런",
                    id: "golearn",
                },
            ]
        }
    },
};
</script>

<style scoped>
.container {
    max-width: 1440px;
}

.card-col {
    padding: 2%;
}

.link:hover {
    color: purple;
    cursor: pointer;
}
</style>
