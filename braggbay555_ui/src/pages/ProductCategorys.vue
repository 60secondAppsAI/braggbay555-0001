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
            <productCategory-table
            v-if="productCategorys && productCategorys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:productCategorys="productCategorys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-product-categorys="getAllProductCategorys"
             >

            </productCategory-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ProductCategoryTable from "@/components/ProductCategoryTable";
import ProductCategoryService from "../services/ProductCategoryService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ProductCategoryTable,
  },
  data() {
    return {
      productCategorys: [],
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
    async getAllProductCategorys(sortBy='productCategoryId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ProductCategoryService.getAllProductCategorys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.productCategorys.length) {
					this.productCategorys = response.data.productCategorys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching productCategorys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching productCategory details:", error);
      }
    },
  },
  mounted() {
    this.getAllProductCategorys();
  },
  created() {
    this.$root.$on('searchQueryForProductCategorysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllProductCategorys();
    })
  }
};
</script>
<style></style>
