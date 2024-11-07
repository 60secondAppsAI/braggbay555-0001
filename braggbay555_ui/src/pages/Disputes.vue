<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <dispute-table
            v-if="disputes && disputes.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:disputes="disputes"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-disputes="getAllDisputes"
             >

            </dispute-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DisputeTable from "@/components/DisputeTable";
import DisputeService from "../services/DisputeService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DisputeTable,
  },
  data() {
    return {
      disputes: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllDisputes(sortBy='disputeId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DisputeService.getAllDisputes(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.disputes.length) {
					this.disputes = response.data.disputes;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching disputes:", error);
        }
        
      } catch (error) {
        console.error("Error fetching dispute details:", error);
      }
    },
  },
  mounted() {
    this.getAllDisputes();
  },
  created() {
    this.$root.$on('searchQueryForDisputesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDisputes();
    })
  }
};
</script>
<style></style>
