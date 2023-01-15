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
import { Birthcertificate } from './birthcertificate';
import { Client } from './client';
import { Imageadditionaldocument } from './imageadditionaldocument';
import { Lostcertificate } from './lostcertificate';
import { Nationalitycertificate } from './nationalitycertificate';

export interface Cni { 
    idDocumentReference?: number;
    documentNumber?: string;
    referenceNumber?: string;
    lastName?: string;
    firstName?: string;
    dateOfBirth?: Date;
    gender?: Cni.GenderEnum;
    profession?: string;
    nameOfFather?: string;
    nameOfMother?: string;
    deliveryDate?: Date;
    expirationDate?: Date;
    address?: string;
    agent?: Agent;
    client?: Client;
    imageadditionaldocument?: Imageadditionaldocument;
    statusTreatmentSystemeList?: Cni.StatusTreatmentSystemeListEnum;
    statusInTreatment?: Cni.StatusInTreatmentEnum;
    withdrawalDate?: Date;
    birthcertificate?: Birthcertificate;
    nationalitycertificate?: Nationalitycertificate;
    lostcertificate?: Lostcertificate;
    biometric?: Biometric;
    posteIdentification?: string;
}
export namespace Cni {
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