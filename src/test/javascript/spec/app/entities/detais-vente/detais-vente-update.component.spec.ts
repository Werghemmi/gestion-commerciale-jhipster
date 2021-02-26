import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { DetaisVenteUpdateComponent } from 'app/entities/detais-vente/detais-vente-update.component';
import { DetaisVenteService } from 'app/entities/detais-vente/detais-vente.service';
import { DetaisVente } from 'app/shared/model/detais-vente.model';

describe('Component Tests', () => {
  describe('DetaisVente Management Update Component', () => {
    let comp: DetaisVenteUpdateComponent;
    let fixture: ComponentFixture<DetaisVenteUpdateComponent>;
    let service: DetaisVenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [DetaisVenteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DetaisVenteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetaisVenteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetaisVenteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetaisVente(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetaisVente();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
