<template>
	<v-hover v-slot="{ hover }">
		<v-card
			:elevation="hover ? 12 : 0"
			height="300"
			width="100%"
			style="position:relative; min-width:250px; cursor: pointer;"
		>
			<v-img
				:src="item.thumbnail"
				class="white--text align-end"
				gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
				height="210px"
				@click="$router.push('/channel/play')"
			>
			</v-img>
			<v-card-actions
				style="display:flex; justify-content:space-between; height:90px;"
			>
				<v-row>
					<v-col class="card-col text-left" cols="2">
						<v-avatar size="36" color="grey">
							<img :src="item.profile" />
						</v-avatar>
					</v-col>
					<v-col class="card-col" cols="10">
						<v-row style="font-weight:bold">
							<span class="link">{{ item.title }}</span>
						</v-row>
						<v-row>
							<span class="link"
								>{{ item.nickname }}({{ item.id }})</span
							>
						</v-row>
						<v-row>
							<v-chip
								class="mr-1"
								v-for="(tag, i) in item.tags"
								:key="i"
								small
								>{{ tag }}</v-chip
							>
						</v-row>
					</v-col>
				</v-row>
			</v-card-actions>
		</v-card>
	</v-hover>
</template>
<script>
export default {
	components: {},
	props: ['item'],
};
</script>

<style></style>
