/**
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

export interface Agent { 
    id?: number;
    email?: string;
    personStatus?: Agent.PersonStatusEnum;
    agentLastName?: string;
    agentFirstName?: string;
    login?: string;
    password?: string;
}
export namespace Agent {
    export type PersonStatusEnum = 'CREATE' | 'ACTIVE' | 'INACTIVE';
    export const PersonStatusEnum = {
        CREATE: 'CREATE' as PersonStatusEnum,
        ACTIVE: 'ACTIVE' as PersonStatusEnum,
        INACTIVE: 'INACTIVE' as PersonStatusEnum
    };
}