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
            <returnRequest-table
            v-if="returnRequests && returnRequests.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:returnRequests="returnRequests"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-return-requests="getAllReturnRequests"
             >

            </returnRequest-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ReturnRequestTable from "@/components/ReturnRequestTable";
import ReturnRequestService from "../services/ReturnRequestService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ReturnRequestTable,
  },
  data() {
    return {
      returnRequests: [],
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
    async getAllReturnRequests(sortBy='returnRequestId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ReturnRequestService.getAllReturnRequests(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.returnRequests.length) {
					this.returnRequests = response.data.returnRequests;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching returnRequests:", error);
        }
        
      } catch (error) {
        console.error("Error fetching returnRequest details:", error);
      }
    },
  },
  mounted() {
    this.getAllReturnRequests();
  },
  created() {
    this.$root.$on('searchQueryForReturnRequestsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllReturnRequests();
    })
  }
};
</script>
<style></style>
