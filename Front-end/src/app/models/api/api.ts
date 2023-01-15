export * from './clientRestController.service';
import { ClientRestControllerService } from './clientRestController.service';
export * from './fileController.service';
import { FileControllerService } from './fileController.service';
export * from './loginController.service';
import { LoginControllerService } from './loginController.service';
export * from './referenceDocumentController.service';
import { ReferenceDocumentControllerService } from './referenceDocumentController.service';
export const APIS = [ClientRestControllerService, FileControllerService, LoginControllerService, ReferenceDocumentControllerService];
