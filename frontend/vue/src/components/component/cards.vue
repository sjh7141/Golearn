<template>
	<v-row>
		<template v-for="i in items">
			<v-col
				:key="i"
				cols="12"
				sm="6"
				md="4"
				lg="3"
				xl="3"
				align="center"
			>
				<v-hover v-slot="{ hover }">
					<v-card
						:elevation="hover ? 12 : 0"
						height="300"
						style="position:relative; min-width:250px;"
					>
						<v-img
							:src="i.thumbnail"
							class="white--text align-end"
							gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
							width="100%"
							height="210px"
						>
						</v-img>
						<v-card-actions
							style="width:100%; display:flex; justify-content:space-between; height:90px;"
						>
							<v-row>
								<v-col class="card-col text-left" cols="2">
									<v-avatar size="36" color="grey">
										<img :src="i.profile" />
									</v-avatar>
								</v-col>
								<v-col class="card-col" cols="10">
									<v-row style="width:100%; font-weight:bold">
										<span class="link">{{ i.title }}</span>
									</v-row>
									<v-row style="width:100%">
										<span class="link"
											>{{ i.nickname }}({{ i.id }})</span
										>
									</v-row>
									<v-row style="width:100%;">
										<v-chip
											class="mr-1"
											v-for="(tag, i) in i.tags"
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
			</v-col>
		</template>
	</v-row>
</template>
<script>
export default {
	props: ['items'],
};
</script>

<style></style>
