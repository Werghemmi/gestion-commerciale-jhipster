import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { ReglementVenteUpdateComponent } from 'app/entities/reglement-vente/reglement-vente-update.component';
import { ReglementVenteService } from 'app/entities/reglement-vente/reglement-vente.service';
import { ReglementVente } from 'app/shared/model/reglement-vente.model';

describe('Component Tests', () => {
  describe('ReglementVente Management Update Component', () => {
    let comp: ReglementVenteUpdateComponent;
    let fixture: ComponentFixture<ReglementVenteUpdateComponent>;
    let service: ReglementVenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [ReglementVenteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ReglementVenteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReglementVenteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReglementVenteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReglementVente(123);
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
        const entity = new ReglementVente();
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
