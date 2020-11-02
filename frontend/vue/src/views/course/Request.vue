<template>
	<div id="request">
		<v-img width="100%" src="@/assets/bg_skin1.jpg" height="230">
			<v-layout
				style="background-color:rgba(0,0,0,0.4); width:100%;height:100%;"
			>
			</v-layout>
		</v-img>

		<div class="py-5" style="width:1080px; margin:0 auto;" ref="contain">
			<h3 style="">New Merge Request</h3>

			<v-layout class="mt-5">
				<v-flex class="pr-5" md6 lg6 xl6>
					<v-card flat outlined>
						<div
							style="background-color:#fafafa;padding:5px 10px;font-size:14px;font-weight:600"
						>
							Source branch
						</div>
						<v-divider />
						<div style="padding:10px 5px;">
							<div
								style="width:50%;display: inline-block;padding:0 5px;"
							>
								<v-menu offset-y :nudge-bottom="3">
									<template v-slot:activator="{ on, attrs }">
										<v-btn
											class="request-button"
											left
											depressed
											outlined
											dense
											width="100%"
											:ripple="false"
											style="border-color:#e5e5e5; color:2e2e2e; font-size:14px;"
											v-bind="attrs"
											v-on="on"
										>
											s03-final/s03p31a402
											<v-icon
												size="18"
												style="position: absolute; right: 0px;"
											>
												mdi-chevron-down
											</v-icon>
										</v-btn>
									</template>
									<v-card outlined flat class="pa-2">
										<div
											style="text-align:center;width:100%;color:#212121;font-size:13px;font-weight:600;"
										>
											Select source project
										</div>
										<v-divider class="my-2" />
										<div class="dropdown-input">
											<input
												class="dropdown-input-field"
												type="search"
												placeholder="Search projects"
												autocomplete="off"
											/>
										</div>
									</v-card>
								</v-menu>
							</div>
							<div
								style="width:50%;display: inline-block;padding:0 5px;"
							>
								<v-btn
									class="request-button"
									width="100%"
									:ripple="false"
									depressed
									outlined
									dense
									style="border-color:#e5e5e5; color:2e2e2e; font-size:14px;"
								>
									feature/auth-service
									<v-icon
										size="18"
										style="position: absolute; right: 0px;"
									>
										mdi-chevron-down
									</v-icon>
								</v-btn>
							</div>
						</div>
					</v-card>
				</v-flex>
				<v-flex class="pl-5" md6 lg6 xl6>
					<v-card flat outlined>
						<div
							style="background-color:#fafafa;padding:5px 10px;font-size:14px;font-weight:600"
						>
							Target branch
						</div>
						<v-divider />
						<div style="padding:10px 5px;">
							<div
								style="width:50%;display: inline-block;padding:0 5px;"
							>
								<v-btn
									class="request-button"
									left
									depressed
									outlined
									dense
									width="100%"
									style="border-color:#e5e5e5; color:2e2e2e; font-size:14px;"
									:ripple="false"
								>
									s03-final/s03p31a402
									<v-icon
										size="18"
										style="position: absolute; right: 0px;"
									>
										mdi-chevron-down
									</v-icon>
								</v-btn>
							</div>
							<div
								style="width:50%;display: inline-block;padding:0 5px;"
							>
								<v-btn
									class="request-button"
									width="100%"
									depressed
									outlined
									dense
									style="border-color:#e5e5e5; color:2e2e2e; font-size:14px;"
									:ripple="false"
								>
									feature/auth-service
									<v-icon
										size="18"
										style="position: absolute; right: 0px;"
									>
										mdi-chevron-down
									</v-icon>
								</v-btn>
							</div>
						</div>
					</v-card>
				</v-flex>
			</v-layout>
		</div>
	</div>
</template>

<script>
export default {
	name: 'Request',
	data() {
		return {};
	},
};
</script>

<style>
#request h3 {
	font-weight: 500;
}
.request-button {
	text-transform: none;
	font-weight: 400;
	font-family: default;
}

.request-button .v-btn__content {
	display: inline;
}

.v-menu__content {
	border: 1px solid #e5e5e5;
	border-radius: 3px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.dropdown-input {
	margin-bottom: 10px;
	font-size: 14px;
}

.dropdown-input-field {
	display: block;
	width: 100%;
	min-height: 30px;
	padding: 0 7px;
	color: #555555;
	line-height: 30px;
	border: 1px solid #73afea;
	border-radius: 2px;
	outline: 0;
	box-shadow: 0 0 4px rgba(115, 175, 234, 0.4);
}
</style>
