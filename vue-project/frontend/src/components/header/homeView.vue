<template>
    <v-container>
        <h1>Products</h1>
        <v-card>
            <v-layout>
                <v-flex xs3>
                    <v-img contain height="125px"></v-img>
                </v-flex>
                <v-layout column>
                    <v-card-title></v-card-title>
                    <v-card-text></v-card-text>
                    <v-card-actions>
                        <v-btn>Add Cart</v-btn>
                    </v-card-actions>
                </v-layout>
            </v-layout>
        </v-card>
        <v-spacer/>
        <h1>My Cart Items ($ {{totalAmount}}) </h1>
        <v-card>
            <v-layout>
                <v-flex xs3>
                    <v-img contain height="125px"></v-img>
                </v-flex>
                <v-layout column>
                    <v-card-title></v-card-title>
                    <v-card-text></v-card-text>
                </v-layout>
            </v-layout>
        </v-card>
    </v-container>
</template>

<script lang="js">
    export default {
        data() {
            return {
                products: [
                ],
                cartItems: [
                ],
                totalAmount: 0
            }
        },
        methods: {
        }
    }
</script>