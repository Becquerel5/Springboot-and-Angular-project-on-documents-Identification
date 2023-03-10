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
import { Agent } from './agent';
import { Biometric } from './biometric';
import { Client } from './client';
import { Imageadditionaldocument } from './imageadditionaldocument';

export interface Passport { 
    idDocumentReference?: number;
    documentNumber?: string;
    referenceNumber?: string;
    lastName?: string;
    firstName?: string;
    dateOfBirth?: Date;
    gender?: Passport.GenderEnum;
    profession?: string;
    nameOfFather?: string;
    nameOfMother?: string;
    deliveryDate?: Date;
    expirationDate?: Date;
    address?: string;
    agent?: Agent;
    client?: Client;
    imageadditionaldocument?: Imageadditionaldocument;
    statusTreatmentSystemeList?: Passport.StatusTreatmentSystemeListEnum;
    statusInTreatment?: Passport.StatusInTreatmentEnum;
    withdrawalDate?: Date;
    country?: string;
    biometric?: Biometric;
}
export namespace Passport {
    export type GenderEnum = 'FEMALE' | 'MALE';
    export const GenderEnum = {
        FEMALE: 'FEMALE' as GenderEnum,
        MALE: 'MALE' as GenderEnum
    };
    export type StatusTreatmentSystemeListEnum = 'FORM' | 'VALIDATE' | 'BUILD' | 'EMIT' | 'FINISH';
    export const StatusTreatmentSystemeListEnum = {
        FORM: 'FORM' as StatusTreatmentSystemeListEnum,
        VALIDATE: 'VALIDATE' as StatusTreatmentSystemeListEnum,
        BUILD: 'BUILD' as StatusTreatmentSystemeListEnum,
        EMIT: 'EMIT' as StatusTreatmentSystemeListEnum,
        FINISH: 'FINISH' as StatusTreatmentSystemeListEnum
    };
    export type StatusInTreatmentEnum = 'Ready' | 'Waiting' | 'Done';
    export const StatusInTreatmentEnum = {
        Ready: 'Ready' as StatusInTreatmentEnum,
        Waiting: 'Waiting' as StatusInTreatmentEnum,
        Done: 'Done' as StatusInTreatmentEnum
    };
}