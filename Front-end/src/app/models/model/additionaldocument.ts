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
import { Byte } from '@angular/compiler/src/util';
import { Agent } from './agent';
import { Client } from './client';
import { Referencedocument } from './referencedocument';

export class Additionaldocument { 
    idAdditionalDocument?: number;
    pathDocumentImage?: string;
    dateAdded?: Date;
    agent?: Agent;
    client?: Client;
    referenceDocument?: Referencedocument;
}